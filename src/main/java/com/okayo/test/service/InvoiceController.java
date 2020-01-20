package com.okayo.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.okayo.test.api.invoice.InvoiceService;
import com.okayo.test.api.invoice.command.CreateInvoiceCommand;
import com.okayo.test.model.invoice.InvoiceDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@Api(tags = {"Invoice"})
@RequestMapping("/invoice")
public class InvoiceController {

  @Autowired InvoiceService invoiceService;

  @ApiOperation(value = "Create invoice")
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<InvoiceDto> createUser(@RequestBody CreateInvoiceCommand command)
      throws Exception {
    return new ResponseEntity<>(invoiceService.createInvoice(command), HttpStatus.OK);
  }
}
