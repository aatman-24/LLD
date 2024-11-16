#### Design Problem: Coffee Vending Machine

---

[Problems](https://github.com/ashishps1/awesome-low-level-design/blob/main/problems/parking-lot.md)

#### Requirements

```tex
The coffee vending machine should support different types of coffee, such as espresso, cappuccino, and latte.
Each type of coffee should have a specific price and recipe (ingredients and their quantities).
The machine should have a menu to display the available coffee options and their prices.
Users should be able to select a coffee type and make a payment.
The machine should dispense the selected coffee and provide change if necessary.
The machine should track the inventory of ingredients and notify when they are running low.
The machine should handle multiple user requests concurrently and ensure thread safety.
```

### Solution

#### 

#### <u>Identify Flow</u>

```textile
During initialization => staff load the coffee machine with ingredients and available coffee
User come to Vending Machine => Hit Button(Show Menu) => Select Coffee => Machine check all required ingredients are avaialble in machine
 => if yes, make coffee => accept coffee => dispense coffee
 => if no, "insufficient ingredient, notify to staff"
```

#### <u>Entity LookUp</u>

```tex
VendingMachine
Coffee
Ingredient
PaymentGateway
```

#### <u>Relationship Lookup</u>

```tex
VendingMachine have an Ingredients
VendingMachine has a PaymentGateway
Coffee have a Ingredients
```

#### <u>Applying SOLID Principle | Design Patterns</u>

```tex
VendingMachine => Singleton (no doubt)
Payment => we can use strategy design pattern if person ask for that
```

#### <u>Class Diagram</u>

```tex
Coffee
- name: String
- price: int
- ingredients: Map<Ingredient, int> (Ingredient, Qty)
--
+ getIngredients(): Map<Ingredient, int>

Ingredient
- name: String
- price: int

IPaymentGateway(interface)
+ accept(int amount): boolean

NetBankingPaymentGateway(IPaymentGateway) && CreditCardPaymentGateway(IPaymentGateway)

VendingMachine
- availableIngredients: Map<String, int>
- paymentGateway: PaymentGateway
---
- initializeCoffee(): void
- loadIngredients(): void
+ showMenu(): void         // show the coffee
+ selectCoffee(String name): Coffee     // select coffee by name
+ makePayment(Coffee coffee, int amount): Map<boolean, Integer> (t/f, change)
+ disepenseCoffee(): void
```

#### <u>Jump to Code</u>

## My-Approach:

This is approach I came up, added more complexity, see in system design one problem can be solved multiple ways, so it is not incorrect that is I'm sure, might be some portion is incorrect, not entire solution. 

#### <u>Identify Flow</u>

User come to Vending Machine => Machine is in idleState => Hit Button(Show Menu) => Machine(ReadyState) => Select Plain Coffee => Top of that, select & add ingredients => Checkout => Pay the total amounts => (DispenseState) receive the coffee => receive the change => IdleState

#### <u>Entity LookUp</u>

VendingMachine
Coffee
CoffeeType (Espressso, Capuccino, Latte)
Ingredient

#### <u>Relationship Lookup</u>

VendingMachine have an Ingredients
VendingMachine can hold selectedCoffee(which is getting processed)

#### <u>Class Diagram(Before)</u>

```tex
Coffee(Abstract)
- type: CoffeeType
- price: int

EspresssoCoffee, CapuccinoCoffee, LatteCoffee (extends Coffee)

---

Ingredient
- name: String
- price: int

---

VendingMachine
- ingredients: Map<String, int>
- selectedCoffee: Coffee
- idleState: VendingMachineState (IdelState) (default)
- readyState: VendingMachineState
- dispenseState: VendingMachineState
---
+ setState(VendingMachineState ): void
+ showMenu(): void
+ showIngredient(): void
+ addIngredient(): Coffee
+ selectCoffee(): Coffee     // pickup base coffee
```

#### <u>Applying SOLID Principle | Design Patterns</u>

```tex
Very clear, similar to pizza toppings... here we have coffee with ingredient

so after addition of ingredient..coffee still remain coffee with but diff ingredient...

so we will apply Decorator design pattern here...

Decorator => Wrapper class have both "is-a" and "has-a" relationship with base class. 

Ingredient(Coffee) (Abstract)
- baseCoffee: Coffee
- name: String
- price: int 
--
+ CoffeeIngredient(Coffee baseCoffee): void
+ getPrice(): int (recursive call on this.getPrice() + ingredient.getPrice())

It have concrete classes like.... 
SugarIngredient, MilkIngredient
```
