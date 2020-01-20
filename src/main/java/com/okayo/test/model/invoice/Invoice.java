package com.okayo.test.model.invoice;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.BatchSize;

import com.okayo.test.model.company.Company;
import com.okayo.test.model.company.ICompany;
import com.okayo.test.model.invoice.line.InvoiceLine;
import com.okayo.test.model.user.IUser;
import com.okayo.test.model.user.User;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Table
@BatchSize(size = 100)
@Entity(name = "invoice")
@Getter
@Setter(value = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Invoice implements IInvoice {
  @Id private String reference;

  private String comment;
  private Instant emissionDate;
  private Instant dueDate;
  private String paymentCondition;
  private BigDecimal price;

  @Valid
  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
  @JoinColumn(name = "user_id")
  private IUser user;

  @Valid
  @NotNull
  @OneToMany(fetch = FetchType.LAZY, targetEntity = InvoiceLine.class)
  @JoinColumn(name = "invoice_id")
  @OrderColumn(name = "order")
  private Set<InvoiceLine> lineList;

  @Valid
  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Company.class)
  @JoinColumn(name = "company_id")
  private ICompany company;

  public Invoice(IUser user, ICompany company) {
    this.reference = UUID.randomUUID().toString();
    this.lineList = new LinkedHashSet<>();
    this.company = company;
    this.user = user;
    this.price = new BigDecimal(0);
  }

  public void addLine(InvoiceLine line) {
    lineList.add(line);

    this.price = price.add(line.getPrice());
  }

  public void updateInformation(String comment, Long dueDateTimestamp, String paymentCondition) {
    this.dueDate = dueDateTimestamp != null ? Instant.ofEpochMilli(dueDateTimestamp) : this.dueDate;
    this.comment = comment != null ? comment : this.comment;
    this.paymentCondition = paymentCondition != null ? paymentCondition : this.paymentCondition;
  }

  public void generate() {
    emissionDate = Instant.now();
  }

  public boolean isLock() {
    return emissionDate != null;
  }
}
