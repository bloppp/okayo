package com.okayo.test.api.invoice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okayo.test.api.invoice.command.AddLineCommand;
import com.okayo.test.api.invoice.command.CreateInvoiceCommand;
import com.okayo.test.api.invoice.command.GenerateInvoiceCommand;
import com.okayo.test.api.invoice.command.UpdateInvoiceInformationCommand;
import com.okayo.test.model.company.CompanyRepository;
import com.okayo.test.model.company.ICompany;
import com.okayo.test.model.invoice.Invoice;
import com.okayo.test.model.invoice.InvoiceDto;
import com.okayo.test.model.invoice.InvoiceRepository;
import com.okayo.test.model.invoice.line.InvoiceLine;
import com.okayo.test.model.user.IUser;
import com.okayo.test.model.user.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class InvoiceService {

  @Autowired InvoiceRepository invoiceRepository;
  @Autowired UserRepository userRepository;
  @Autowired CompanyRepository companyRepository;

  public InvoiceDto createInvoice(CreateInvoiceCommand command) throws Exception {

    log.info("creation invoice: %s", command.toString());

    IUser user = userRepository.findById(command.getUserId());

    if (user == null) {
      throw new Exception("user not found");
    }

    ICompany company = companyRepository.findById(command.getCompanyId());

    if (company == null) {
      throw new Exception("company not found");
    }

    Invoice invoice = new Invoice(user, company);

    invoiceRepository.save(invoice);

    log.info("invoice created: %s", command.toString());

    return new InvoiceDto(invoice);
  }

  public InvoiceDto addLine(AddLineCommand command) throws Exception {

    log.info("adding line to invoice: %s", command.toString());

    Invoice invoice = invoiceRepository.findForUpdateByReference(command.getInvoiceReference());
    if (invoice == null) {
      throw new Exception("invoice not found");
    }
    if (invoice.isLock()) {
      throw new Exception("invoice is locked");
    }

    invoice.addLine(
        new InvoiceLine(
            UUID.randomUUID(),
            command.getName(),
            BigDecimal.valueOf(command.getQuantity()).setScale(2, RoundingMode.DOWN),
            BigDecimal.valueOf(command.getVatRate()).setScale(2, RoundingMode.DOWN),
            BigDecimal.valueOf(command.getDiscount()).setScale(0, RoundingMode.DOWN)));

    invoiceRepository.save(invoice);

    log.info("line added to invoice: %s", command.toString());

    return new InvoiceDto(invoice);
  }

  public InvoiceDto updateInvoiceInformation(UpdateInvoiceInformationCommand command)
      throws Exception {

    log.info("update invoice information: %s", command.toString());

    Invoice invoice = invoiceRepository.findForUpdateByReference(command.getReference());

    if (invoice == null) {
      throw new Exception("invoice not found");
    }
    if (invoice.isLock()) {
      throw new Exception("invoice is locked");
    }

    invoice.updateInformation(
        command.getComment(), command.getDueTimestamp(), command.getPaymentCondition());

    log.info("invoice information updated: %s", command.toString());

    return new InvoiceDto(invoice);
  }

  public InvoiceDto generateInvoice(GenerateInvoiceCommand command) {

    log.info("generate invoice: %s", command.toString());

    Invoice invoice = invoiceRepository.findForUpdateByReference(command.getReference());

    invoice.generate();

    log.info("invoice generated: %s", command.toString());

    return new InvoiceDto(invoice);
  }
}
