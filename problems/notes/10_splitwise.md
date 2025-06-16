## Design Problems: Splitwise

---

### Requirement

```tex
A user can create an expense and split it with one or more users.
The system should keep track of who owes how much to whom.
Support for simplification of debts is a plus but not mandatory for MVP.

A user should be able to add an expense, specifying:
Total amount
Who paid the amount
List of users involved
How to split (equal / exact / percentage)
The system should be able to show balances:
For a single user: how much they owe and are owed
For all users: overall balances
The system should handle multiple expenses across different groups of users.
[Optional for Bonus]: Implement a method to simplify debts — reduce the number of transactions between users to settle up.
```



### Identify Flow

```textile
Open the application.

Register yourself as a user.

Add friends as direct connections.

Create a group and add those friends to it.

Create an expense by specifying the amount, adding a description, and tagging all the people involved.

Each of the above operations updates the User Balance Sheet, which maintains balance details between the user and their friends — indicating who owes whom and how much.
```

### Entity LookUp

```tex
- Splitwise
- User
- UserBalanceSheet
- Group
- Expense
```

### Relationship Lookup

```tex
Splitwise(1) - User(N)
Splitwise(1) - Group(N)
Group(1) - User(N)
User(1) - UserBalanceSheet(1)
Expense(1) - User(N)
```

### Applying SOLID Principle | Design Patterns

```tex
1. Singleton Pattern for Splitwise
The Splitwise class follows the Singleton design pattern to ensure a single, centralized instance managing the application state.

2. Handling Split Operations – Strategy Pattern
Expense splitting can be performed in multiple ways:

Equal split
Unequal split
Percentage-based split

Since the splitting logic can vary, it is the responsibility of the user creating the expense to define the type of split using:

enum SplitType {
    EQUAL,
    UNEQUAL,
    PERCENTAGE
}
There are two possible scenarios:

Case 1:
The client (frontend) calculates how much each user owes and sends this information to the backend.
→ In this case, the backend must validate the data provided.

Case 2:
The client only provides the split type and relevant meta-info (e.g., ratios or percentages), and the backend is responsible for calculating each user’s share.
→ In this case, the backend performs the calculation based on the inputs.

To support both cases in a flexible and scalable way, we use the Strategy Design Pattern, encapsulating the split logic in dedicated classes:

SplitOperation – Interface
EqualSplitOperation
UnEqualSplitOperation
PercentageSplitOperation – Implementations

3. Managing Users, Groups, and Expenses – Controller Abstraction
We maintain the following entities:

List of users
List of groups
List of expenses

Instead of storing and managing all this logic inside the Splitwise class (which would violate SRP and make the codebase harder to scale), we introduce dedicated controller classes:

UserController
GroupController
ExpenseController

These controllers manage respective entities and expose APIs for interaction.
This approach is beneficial because User, Group, and Expense are dumb objects (POJOs), and the business logic should not reside in them.

4. Managing User Balances – BalanceSheetController
UserBalanceSheet is another dumb object that stores balance information between users—i.e., who owes whom and how much.

However, it requires business logic to update and maintain these balances, so we introduce:

BalanceSheetController – responsible for managing and updating the user balance sheet consistently as expenses are added or modified.
```

### Entity LookUp



```tex
- Splitwise

- User
- UserBalanceSheet
- UserController

- BalanceSheetControlller

- Group
- GroupController

- Expense
- ExpenseController

- SplitOperation
- EqualSplitOperation
- UnEqualSplitOperation
- PercentageSplitOperation

- SplitType
```

 

### Relationship Lookup

```tex
Splitwise(1) - UserController(1)
Splitwise(1) - GroupController(1)
Splitwise(1) - BalanceSheetControlller(1)

UserController - User(N)
User(1) - UserBalanceSheet(1)

UserController - ExpenseController
GroupController - ExpenseController


GroupController - Group(N)
Group(1) - User(N)

Expense(1) - User(N)

SplitOperation 
- EqualSplitOperation
- UnEqualSplitOperation
- PercentageSplitOperation

```



### Class Diagram

```tex
User
- id: String
- name: String
- balanceSheet: UserBalanceSheet

UserController
- users: List<User>
--
+ addUser(User user): void
+ removeUser(User user): void

UserBalanceSheet
- id: String
- userVsBalance: <User, Balance>
- totalPayments: Double 	// total payment he made
- totalExpense: Double		// total expense 
- totalAmountOwe: Double	// negative balance, paid by someone else
- totalAmountGetback: Double
- totalOweAmount: Double
- totalGetBackAmount: Double
--
+ updateBalanceSheetOfUser(User assoicatedUser, Balance balance): void
+ addNewExpense(Double amount): void
+ addNewPayment(Double  amount): void

BalanceSheetControlller
+ addExpense(String expenseId, String description, SplitType splitType, Double totalAmount, User paidBy, List<SplitInfo> splitInfos): Expense
+ showBalanceSheetOfUser(User user): void

Balance
- oweAmount: Double   
- getBackAmount: Double 

Group
- id: String
- name: String
- members: List<User>
- expenses: List<Expense>
- balanceSheetController: BalanceSheetController
--
+ addMember(User user): void
+ addExpense(String expenseId, String description, SplitType splitType, Double totalAmount, User paidBy, List<SplitInfo> splitInfos): Expense

GroupController
- id: String
- groups: List<Group>
--
+ addGroup(Group group): void
+ addMember(Group group, User user): void
+ removeMember(Group group, User user): void
+ + addExpense(Group group, String expenseId, String description, SplitType splitType, Double totalAmount, User paidBy, List<SplitInfo> splitInfos): Expense

Expense
- id: Long
- description: String
- splitType: SplitType
- totalAmount: Double
- paidBy: User
- splitInfo: List<SplitInfo>

SplitInfo
- user: User
- amount: Double
- percentage: Double

SplitOperation
- split(List<SplitInfo> splitInfo, Double totalAmount): Boolean

Splitwise
- userController: UserController
- groupController: GroupController
- balanceSheetController: BalanceSheetController
--
+ addUser(String id, String name): void
+ addGroup(String id, String groupName): void
+ addUserInGroup(User user, Group group): void
+ addExpense(String description, Double amount, SplitType splitType, User paidBy, List<SplitInfo> splitInfo): void
```



### Source Code



https://github.com/aatman-24/LLD/tree/main/problems/src/splitwise
