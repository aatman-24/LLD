### Design Problem: Vending Machine

---

#### Practice:

![](../../assets/2024-10-28-14-13-23-vending_machine.drawio.png)

<u>**Thinking Process:**</u>

<u> Requirements <u>
The vending machine should support multiple products with different prices and quantities.
The machine should accept coins and notes of different denominations.
The machine should dispense the selected product and return change if necessary.
The machine should keep track of the available products and their quantities.
The machine should handle multiple transactions concurrently and ensure data consistency.
The machine should provide an interface for restocking products and collecting money.
The machine should handle exceptional scenarios, such as insufficient funds or out-of-stock products.

<u>Identify Flow</u>

machine_idle => user hit the button to see all the products => select the product => select the qty => machine go in next stage => user enter money =>
machine validate => dispense the product => again become idle. 

Additional_flow: (Hasn't encorporated)
machine_idle => admin come and check stocks => add-stock => machine_idle
machine_idle => admin come and check money collection => collect money => machine_idle

<u>Entity LookUp</u>

VendingMachine
Product
Inventory
Coin 
CoinType (ONE_RUPEES, TWO_RUPEES, TEN_RUPEES, HUNDREAD_RUPEES)

<u>Relationship Lookup</u>

VendingMachine can have many Products. 

<u>Applying SOLID Principle | Design Patterns</u>

VendingMachine => singleton
Product abstract => all the actual products extend it. (Verfiy with Interviewer) 
Now as Machine have different behaviour based upon state, we can implement State Design Pattern. 

<u>Applying State Design Pattern </u>

Reactify flow and Idenify State:

IdealState
ReadyState
CheckoutState
DispenseState

machine_idle => user hit the button to see all the products => add to cart product => select the qty => machine go in next stage => user enter money =>
machine validate => dispense the product => again become idle. 

HappyFlow:
IdelState => showInventory() => ReadyState => selectProduct()/alterSelection() => CheckoutState => acceptCoin() => dispenseState => productDispense() => IdelState

<u>UML Diagram</u>

```textile
Product
- name: String
- code: String
- price: int

Inventory
- products: Product[]
--
+addProduct(Product product): Boolean
+removeProduct(Product product): Boolean
+ displayInventory(): void
<!-- +clearInventory(): void -->
<!-- +checkoutCart(Cart cart): Boolean -->

Cart
- products: Product[]
--
+ addToCart(Product product): void
+ removeProduct(): Boolean
<!-- + checkout(): int -->
+ clear(): Boolean
+ costOfCart(): int

CoinType
- ONE 
- TWO
- TEN
- HUNDREAD

Coin 
- type: CoinType
- value: int

--

VendingMachine
- inventory: Inventory
- cart: Cart
- state: MachineState
- availableBalance: int
- isPaymentReceived: Boolean
- amountOfPayment: int
--
+ displayInventory(): void, Inventory, State
+ displayCart(): void, Cart


+ addToCart(Product product): void
+ removeFromCart(Product product): Boolean
+ clearCart(): Boolean
+ checkout(): Boolean, VendingMachine

+ insertCoins(Coin[] coins): Boolean, VendingMachine
+ acceptPayment(): Boolean, VendingMachine
+ productDispense(): Boolean, VendingMachine + Cart
+ cancel(): void


State
+ displayInventory(): void
+ addToCart(Product product): void
+ removeFromCart(Product product): Boolean
+ clearCart(): Boolean
+ checkout(): Boolean
+ insertCoins(Coin[] coins): Boolean
+ acceptPayment(): Boolean
+ productDispense(): Boolean
+ cancel(): Boolean

IdealState
+ displayInventory(): void
+ cancel(): Boolean

ReadyState
+ addToCart(Product product): void
+ removeFromCart(Product product): Boolean
+ clearCart(): Boolean
+ checkout(): Boolean
+ cancel(): Boolean

PreDispenseState
+ insertCoins(Coin[] coins): Boolean
+ acceptPayment(): Boolean
+ cancel(): Boolean

DispenseState
+ productDispense(): Boolean
+ cancel(): Boolean
```

### Solution:

<img title="" src="../../assets/2024-11-02-12-27-57-vending_machine_solution.png" alt="" width="675">

**UML**

```
Product
- name: String
- price: double

Inventory
- products: Map<Product,Integer>
--
+ addProduct(Product product, int quantity): void
+ removeProduct(Product product)
+ updateQuantity(Product product, int quantity)
+ getQuantity(Product product)
+ isAvailable(Product product)


VendingMachine
- instance: VendingMachine
- inventory: Inventory
- idleState: VendingMachineState
- readyState: VendingMachineState
- dispenseState: VendingMachineState
- returnChangeState: VendingMachineState
- currentState: VendingMachineState
- selectedProduct: Product
- totalPayment: double
--
+ getInstance(): VendingMachine
+ selectProduct(product: Product): void
+ insertCoin(coin: Coin): void
+ insertNote(note: Note): void
+ dispenseProduct(): void
+ returnChange(): void
+ setState(state: VendingMachineState): void
+ getIdleState(): VendingMachineState
+ getReadyState(): VendingMachineState
+ getDispenseState(): VendingMachineState
+ getReturnChangeState(): VendingMachineState
+ getSelectedProduct(): Product
+ setSelectedProduct(product: Product): void
+ resetSelectedProduct(): void
+ getTotalPayment(): double
+ addCoin(coin: Coin): void
+ addNote(note: Note): void
+ resetPayment(): void


VendingMachineState
+ selectProduct(Product product): void
+ insertCoin(Coin coin): void
+ insertNote(Note note): void
+ dispenseProduct(): void
+ returnChange(): void


IdleState
+ selectProduct(Product product): void

ReadyState
+ insertCoin(Coin coin): void
+ insertNote(Note note): void

DispenseState
+ dispenseProduct(): void

ReturnChangeState
+ returnChange(): void

CoinEnum
+ PENNY(0.01)
+ NICKEL(0.05)
+ DIME(0.1)
+ QUARTER(0.25)
--
+ getValue(): double


Note
+ ONE(1)
+ FIVE(5)
+ TEN(10)
+ TWENTY(20)
--
+ getValue(): double
```
