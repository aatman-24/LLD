#### Design Problem: StackOver Flow

---

[Problems](https://github.com/ashishps1/awesome-low-level-design/blob/main/problems/stack-overflow.md)

#### Requirements

1. Users can post questions, answer questions, and comment on questions and answers.
2. Users can vote on questions and answers.
3. Questions should have tags associated with them.
4. Users can search for questions based on keywords, tags, or user profiles.
5. The system should assign reputation score to users based on their activity and the quality of their contributions.
6. The system should handle concurrent access and ensure data consistency.

### Solution

<u>Identify Flow</u>

```tex
    User can post question(title, body, tags). Posting question, user earn some points. 

    User can post comment or answer on that question. Posting answers, if it is correct, user gets some points. 

    User can react(upvote/downvote) on that question/answer. 

    User can search for the question based on (keywords, tags, userProfile)
```

<u>Entity LookUp</u>

```tex
    StackOverflow
    User
    Question
    Answer
    Comment
    Vote
    Tag
```

<u>Relationship Lookup</u>

```tex
    User(1) -> (N)Question 
    User(1) -> (N)Answer
    User(1) -> (N)Comment
    ---
    Question(1) -> (N)Answer
    Question(1) -> (N)Comments
    Question(1) -> (N)Tag 
    Question(1) -> (N)Vote
    ---
    Answer(1) -> (N)Comments
    Answer(1) -> (N)Vote
```

<u>Applying SOLID Principle | Design Patterns</u>

```tex
    1_ StackOverflow => Singleton

    2_ Question & Answer both we can make Comment & do Vote. 

        We can create interface for both, and let Question & Answer implement it. 

        Commentable
            addComment(Comment comment)
            getComments() => return all comments

        Votable
            vote(User user, int value) => upvote/downvote
            getVoteCount() => return total vote count

    3_ Tag, we can create factory method so we cache created Tag. 

        Either we can create separate class for that, or we can 
        consist that logic within the Main StackOverFlow class. 

    4_ Search methods: search by question keywords, search by username, search by tags(on question)

        Brute Force way: We have to iterate over all the users => 

                For each user, we need to check all questions =>

                    then we can check... core logic

                We can do better way by using cache at global level. 

        Inside StackOverFlow class, 

            we store all questions, answers, so we can directly use 
            that during search, and eliminate the users(lookup). 
```

<u>Class Diagram</u>

```tex
User
+ id: int
+ userName: String
+ reputation: int (default = 0)
+ questions: Questions[]
+ answers: Answer[]
+ comments: Comment[]
--
+ askQuestion(String title, String content, List<Strings> Tags): Question
+ answerQuestion(Question que, String content): Answer
+ addComment(Commentable commentable, String content): Comment
+ updateReputation(int value): void

Commentable(I)
+ addComment(Comment comment): void
+ getComments(): List<Comment>

Votable(I)
+ vote(User user, int value): void
+ getVoteCount(): int

Question implements Votable & Commentable
+ id: int
+ title: String
+ content: String
+ author: User
+ creationDate: Date
+ answers: Answer[]
+ comments: Comment[]
+ tags: Tag[]
+ votes: Vote[]
--
+ Question(User author, String title, String content, tags: List<Tag>): void
+ addAnswer(Answer answer): void
+ vote(User user, int value): void
+ getVoteCount(): int
+ addComment(Comment comment): void
+ getComments()


Answer implements Votable & Commentable
+ id: int
+ content: String
+ author: User
+ question: Question
+ isAccepted: Boolean
+ creationDate: Date
+ votes: Vote[]
+ comments: Comment[]
--
+ vote(User user, int value): void
+ getVoteCount(): int
+ addComment(Comment comment): void
+ getComments()
+ markAsAccepted(): void

Comment
+ id: int
+ content: String
+ author: User
+ creationDate: Date

Vote
+ user: User
+ value: int

Tag
+ id: int
+ name: String

StackOverflow
+ users: Map<Integer, User> 
+ questions: Map<Integer, Question> 
+ answers: Map<Integer, Answer> 
+ tags: Map<String, Tag> 
--
+ createUser(String username, String email): User
+ askQuestion(User user, String title, String content, List<String> tags): Question
+ answerQuestion(User user, Question question, String content): Answer
+ addComment(User user, Commentable commentable, String content): Comment
+ voteQuestion(User user, Question question, int value): void
+ voteAnswer(User user, Answer answer, int value): void
+ acceptAnswer(Answer answer): void
+ searchQuestions(String query): List<Question> 
+ getQuestionsByUser(User user): List<Question> 
```

<u>UML Diagram</u>

![](../../assets/2024-11-03-14-51-39-stack_over_flow.drawio.png)

<u>Jump to Code</u>

[Link](https://github.com/ashishps1/awesome-low-level-design/tree/main/solutions/java/src/stackoverflow)

**Important implementation:**

```java
    // StackOverflow.java
    public List<Question> searchQuestions(String query) {
        return questions.values().stream()
                .filter(q -> q.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                        q.getContent().toLowerCase().contains(query.toLowerCase()) ||
                        q.getTags().stream().anyMatch(t -> t.getName().equalsIgnoreCase(query)))
                .collect(Collectors.toList());
    }


    // Question.java
    @Override
    public void vote(User user, int value) {
        if (value != 1 && value != -1) {
            throw new IllegalArgumentException("Vote value must be either 1 or -1");
        }
        // remove vote from same person, add again. 
        votes.removeIf(v -> v.getUser().equals(user));
        votes.add(new Vote(user, value));
        author.updateReputation(value * 5);  // +5 for upvote, -5 for downvote
    }

    @Override
    public int getVoteCount() {
        return votes.stream().mapToInt(Vote::getValue).sum();
    }
```
