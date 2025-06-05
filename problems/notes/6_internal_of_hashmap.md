---

## 🔧 What is a HashMap?

A **HashMap** in Java is a **key-value** data structure used to store data in a way that allows fast insertion and lookup, typically in **O(1)** average time complexity.

It is part of the `java.util` package and is a **concrete implementation of the `Map<K, V>` interface**.

---

## 🧩 Key Concepts of HashMap

### ✅ Two Primary Operations

1. `put(K key, V value)` – Used to store the value.
2. `get(K key)` – Used to retrieve the value using the key.

---

## 🧱 Node Structure in HashMap

Internally, each **bucket** (index in the array) holds a **linked list** (or a red-black tree if it grows too long). Each element of this list is a **Node** object.

```java
static class Node<K,V> implements Map.Entry<K,V> {
    final int hash;
    final K key;
    V value;
    Node<K,V> next;
}
```

### Each Node Has:

* **hash**: Computed from the key's `hashCode()`.
* **key**: The key you provided.
* **value**: The value to store.
* **next**: A pointer to the next node (linked list for handling collisions).

---

## 📦 Initial Setup of HashMap

* By default, the **capacity** of a HashMap is 16.
* The **load factor** is 0.75.
* So, once the number of entries exceeds `16 * 0.75 = 12`, resizing (rehashing) is triggered.

---

## 🔢 How Does `put()` Work?

### Example:

```java
map.put(3, 10);
```

### Steps:

1. Compute the **hash**:

   ```java
   hash = hash(key.hashCode())
   ```

    * This `hash()` method distorts the `hashCode()` to spread it more evenly across buckets.

2. **Index** in the internal array is calculated as:

   ```java
   index = (n - 1) & hash; // n is the capacity
   ```

3. At that **index**:

    * If it’s empty → create new Node and insert.
    * If it already has nodes (collision) → traverse the list to:

        * Check if the key already exists (using `equals()`).
        * If yes → update value.
        * If no → append to the end of the linked list.

---

## 🤔 What if Multiple Keys Map to the Same Index?

This is called a **collision**.

To handle collisions:

1. Initially, a **Linked List** is maintained at that index.
2. If the number of entries at a single index **exceeds 8**, and the overall capacity is >= 64:

    * The linked list is **converted to a Red-Black Tree**.
    * This makes the lookup time **O(log n)** instead of O(n).

### 🌳 Treeification Thresholds:

* If the number of nodes at a bucket > **TREEIFY\_THRESHOLD (8)** → convert to Tree.
* If the number drops below **UNTREEIFY\_THRESHOLD (6)** after deletion → convert back to Linked List.

---

## ♻️ Rehashing – What is It?

When the size exceeds **capacity \* loadFactor**, the HashMap **resizes**.

### What Happens During Rehashing?

1. A new array of **double the size** is created.
2. All existing nodes are **rehashed and redistributed** into the new array using the new capacity.
3. The hash and index might change for each key.

This is an expensive operation (O(n)).

---

## 🧮 Time Complexity

| Operation         | Best/Average Case | Worst Case                        |
| ----------------- | ----------------- | --------------------------------- |
| `put()` / `get()` | O(1)              | O(n) if all keys go to one bucket |
| With Tree         | O(log n)          | Still better than linked list     |
| Rehashing         | -                 | O(n)                              |

---

## 🔐 Role of `hashCode()` and `equals()`

### `hashCode()`

* Returns an integer hash for the key.
* Used to determine the bucket index.

### `equals()`

* Used when multiple keys are at the same index to check if keys are actually equal.

> **Rule**: If `a.equals(b)` is true, then `a.hashCode() == b.hashCode()` must also be true.

But **not** vice versa:

> If `a.hashCode() == b.hashCode()`, `a.equals(b)` may still be false.

---

## 🧪 Example Walkthrough

Let’s walk through a simple `put(3, 10)` with table size = 10.

1. Compute hash:

    * `key = 3 → hashCode = 3 → hash = hash(3)`
2. Index:

    * `index = hash % 10`
3. Suppose index = 2.

    * At table\[2], we insert a node:
      `{ hash: 3, key: 3, value: 10, next: null }`

Now we call `put(13, 20)`:

* Suppose `hash(13) % 10 = 2` too → collision!
* Create a new node and add it to the linked list at table\[2].

So, table\[2] now has:

```
Node1: key=3, value=10 → next →
Node2: key=13, value=20 → next = null
```

---

## 🌳 Treeification In Action

If the list grows beyond 8 elements at table\[i], it becomes:

```java
TreeNode<K,V> extends LinkedHashMap.Entry<K,V> {
    TreeNode<K,V> parent;
    TreeNode<K,V> left;
    TreeNode<K,V> right;
    boolean red;
}
```

This is a Red-Black Tree.

---

## 📉 When Is Tree Reverted Back?

When deleting keys and the number of nodes at a bucket becomes < 6, it is **untreeified** back to a linked list to save memory.

---

## 🔄 Summary of Lifecycle

```text
Add Entry →
  Compute hash →
    Find index →
      If empty: insert →
      Else: check equals() →
        If same: update →
        Else: add to linked list →
            If size > 8 and table size ≥ 64: convert to tree →
Resize when loadFactor exceeded →
  Create new array →
  Rehash and redistribute all keys.
```

---

## 📝 Additional Points

* `HashMap` is **not thread-safe**. Use `ConcurrentHashMap` for multi-threaded environments.
* `null` is allowed as a key and value (but only one null key).
* `HashMap` does not maintain order. For order-preserving maps, use `LinkedHashMap`.

---

Would you like a diagram of how this internal structure looks visually (like linked lists and trees inside a HashMap)?
