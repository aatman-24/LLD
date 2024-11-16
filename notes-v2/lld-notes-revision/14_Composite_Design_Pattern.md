### Design Pattern: Composite Design (Structural Design Pattern)

---

> **Composite** is a structural design pattern that lets you compose objects into tree structures and then work with these structures as if they were individual objects.



**Problem:**

It makes sense only when the core model of your app can be represented as a tree.

For example, imagine that you have two types of objects: `Products` and `Boxes`. A `Box` can contain several `Products` as well as a number of smaller `Boxes`. These little `Boxes` can also hold some `Products` or even smaller `Boxes`, and so on.

Say you decide to create an ordering system that uses these classes. Orders could contain simple products without any wrapping, as well as boxes stuffed with products...and other boxes. How would you determine the total price of such an order?

![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-10-20-12-31-32-image.png)

The first approach comes into the mind is that, unwrap the box and go all over the products and calculate the cost, but for that you need to know the classes of `Product`. Which is hard to implement when you have 1000 of products in your system. 



**Solution:**



That's where, this pattern helps you to unwrap the products and calculate the cost without knowing what product type it is. How, Let's see!



<mark>**Unified Interface**</mark>

The Composite pattern suggests that you work with `Products` and `Boxes` through a common interface which declares a method for calculating the total price.



How would this method work? For a product, it’d simply return the product’s price. For a box, it’d go over each item the box contains, ask its price and then return a total for this box. If one of these items were a smaller box, that box would also start going over its contents and so on, until the prices of all inner components were calculated. A box could even add some extra cost to the final price, such as packaging cost.



Such execution is possible due to common interface, and whatever product we get during recursion also implemented that interface, so we can call common method which we need through interface, don't need to know its concrete class. 



**Structure:**



![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-10-20-12-38-14-image.png)



![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-10-20-12-38-57-image.png)



**Pseudocode:**



![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-10-20-12-39-12-image.png)



**Where we can apply it:**



![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-10-20-12-40-01-image.png)


