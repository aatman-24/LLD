### Design Pattern: Abstract Factory

----

> **Abstract Factory** is a creational design pattern that lets you produce families of related objects without specifying their concrete classes.



<u>Problem it solves:</u>



Suppose, We are developing one game which have two different them. Monsoon And Summer Theme. So both Theme have same elements in game(road, sky...) but theme is different based on selected theme. And due to that we have to make sure, when we create Monsoon them..that time all the elements should be created of that them only. 



Let's see simliar examples and try to solve that using Abstract Factory:



![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-10-01-20-25-38-image.png)



Here we have 3 theme(Art Deco, Victorian and Modern) and respective products for each theme. 



Firstly, we have declare interface for each of product. `Chair`, `Sofa` and `CoffeeTable`   And extend each of them by Theme type. Like this...



![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-10-01-20-27-38-image.png)



Next, we will create one abstract factory, which produce all those Product. This is "Abstract Factory". 



![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-10-01-20-28-23-image.png)



But we need them specific factory so that, each them create similar kinds of objects. For that we have to implement this interface with theme specific way.



![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-10-01-20-29-30-image.png)



![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-10-01-20-30-32-image.png)



Example:



![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-10-01-20-31-10-image.png)



Suppose, we have selected GUI as "win"... which basically create one factory this way.

        `GUIFactory factory = new WinFactory()`

 whenever we need to create Button that time it will create from "WinFactory" only.
