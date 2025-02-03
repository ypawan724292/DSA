package lld.solutions.splitwise

import splitwise.splittype.EqualSplit

object SplitwiseDemo {
    fun run() {
        val splitwiseService: SplitwiseService = SplitwiseService.Companion.getInstance()

        // Create users
        val user1 = User("1", "Alice", "alice@example.com")
        val user2 = User("2", "Bob", "bob@example.com")
        val user3 = User("3", "Charlie", "charlie@example.com")

        splitwiseService.addUser(user1)
        splitwiseService.addUser(user2)
        splitwiseService.addUser(user3)

        // Create a group
        val group = Group("1", "Apartment")
        group.addMember(user1)
        group.addMember(user2)
        group.addMember(user3)

        splitwiseService.addGroup(group)

        // Add an expense
        val expense = Expense("1", 300.0, "Rent", user1)
        val equalSplit1: EqualSplit = EqualSplit(user1)
        val equalSplit2: EqualSplit = EqualSplit(user2)
        val percentSplit: PercentSplit = PercentSplit(user3, 20.0)

        expense.addSplit(equalSplit1)
        expense.addSplit(equalSplit2)
        expense.addSplit(percentSplit)

        splitwiseService.addExpense(group.getId(), expense)

        // Settle balances
        splitwiseService.settleBalance(user1.getId(), user2.getId())
        splitwiseService.settleBalance(user1.getId(), user3.getId())

        // Print user balances
        for (user in Arrays.asList<User>(user1, user2, user3)) {
            println("User: " + user.getName())
            for (entry in user.getBalances().entries) {
                println("  Balance with " + entry.key + ": " + entry.value)
            }
        }
    }
}
