

# Payment System – Bridge Pattern Example

## Overview

### Resources were used: 
* https://refactoring.guru/design-patterns/bridge
* https://refactoring.guru/design-patterns/bridge/java/example

This project demonstrates the **Bridge Design Pattern** using a simple payment system.
The Bridge pattern is used to **decouple an abstraction from its implementation** so that both can evolve independently.

In this example:

* The **Abstraction** represents different types of payments (e.g., `OnlinePayment`, `SubscriptionPayment`).
* The **Implementation** represents payment providers (`Visa`, `MasterCard`).
* The **Bridge** connects a payment type to a payment provider, allowing flexible combinations.

---

##  Why Bridge Pattern?

Without the Bridge pattern, if we wanted multiple payment types (Online, Subscription, etc.) and multiple providers (Visa, MasterCard, PayPal), we would end up with **many redundant subclasses**:

* `OnlineVisaPayment`
* `OnlineMasterCardPayment`
* `SubscriptionVisaPayment`
* `SubscriptionMasterCardPayment`
* …and so on.

The Bridge pattern avoids this explosion by **separating concerns**:

* Payment types handle *what operation is performed*.
* Providers handle *how the operation is executed*.

---

##  Structure in this project

### Abstraction - high-level control interface.

* **`IPaymentType`** – interface defining payment operations:

    * `processPayment(double sum)`
    * `refundPayment(double sum)`

* **Refined Abstractions:**

    * `OnlinePayment` – represents online transactions.
    * `SubscriptionPayment` – represents recurring subscription transactions.

Both hold a reference to a `PaymentProvider` and a `BankAccount`.

---

### Implementation - low-level operations.

* **`IPaymentProvider`** – interface defining provider-specific actions:

    * `capture(BankAccount user, double sum)`
    * `refund(BankAccount user, double sum)`
    * `getName()`

* **Concrete Implementations:**

    * `Visa`
    * `MasterCard`

These classes implement provider-specific logic (e.g., deducting or refunding money).

---

### Domain Object

* **`BankAccount`** – represents a user’s account with a balance.
  Provides methods:

    * `debit(double sum)`
    * `refund(double sum)`
    * `getBalance()`

---

##  How the Bridge Works Here

* `PaymentType` (e.g., `OnlinePayment`) **delegates** its operations (`processPayment`, `refundPayment`) to a chosen `PaymentProvider` (e.g., `Visa`, `MasterCard`).
* The user interacts with **PaymentType** only, without worrying about the underlying provider.

This allows easy mix-and-match:

* `OnlinePayment + Visa`
* `OnlinePayment + MasterCard`
* `SubscriptionPayment + Visa`
* …and more, without new subclasses.

---

##  Examples

### Example 1 (MasterCard)

```java
BankAccount user = new BankAccount(1000);
IPaymentType type = new OnlinePayment(user, new MasterCard());

type.processPayment(10);   // Pay 10
type.refundPayment(10);    // Refund 10
type.processPayment(900.99); // Pay 900.99
```

### Example 2 (Visa)

```java
BankAccount user = new BankAccount(1000);
IPaymentType type = new OnlinePayment(user, new Visa());

type.processPayment(30.99);  // Pay 30.99
type.processPayment(990);    // Try to pay 990 (may fail if insufficient funds)
```

---

##  Benefits of Bridge Pattern in this Project

* **Flexibility** – Easily add new payment types or providers independently.
* **Reduced Class Explosion** – No need for every combination of PaymentType + Provider.
* **Clean Separation** – Business logic (payment type) is separated from provider logic.
* **Scalability** – Easy to add `PayPal` or `CryptocurrencyProvider` later without changing existing payment types.

---

##  Project Structure

```
src/
 ├── Main.java                # Demo runner
 ├── user/
 │    └── BankAccount.java    # Domain object
 ├── type/
 │    ├── IPaymentType.java   # Abstraction
 │    ├── OnlinePayment.java  # Refined Abstraction
 │    └── SubscriptionPayment.java
 └── provider/
      ├── IPaymentProvider.java # Implementation interface
      ├── Visa.java             # Concrete Implementation
      └── MasterCard.java       # Concrete Implementation
```

---
