const express = require('express');
const app = express();
const PORT = 3000;

// In-memory data store
let books = [
  { id: 1, title: 'Book 1', author: 'Author 1' },
  { id: 2, title: 'Book 2', author: 'Author 2' },
];

app.use(express.json());

// Get all books
app.get('/books', (req, res) => {
  res.json(books);
});

// Get a specific book by ID
app.get('/books/:id', (req, res) => {
  const bookId = parseInt(req.params.id);
  const book = books.find(book => book.id === bookId);
  if (!book) {
    return res.status(404).json({ message: 'Book not found' });
  }
  res.json(book);
});

// Create a new book
app.post('/books', (req, res) => {
  const { title, author } = req.body;
  const newBook = { id: books.length + 1, title, author };
  books.push(newBook);
  res.status(201).json(newBook);
});

// Update a book by ID
app.put('/books/:id', (req, res) => {
  const bookId = parseInt(req.params.id);
  const { title, author } = req.body;
  const book = books.find(book => book.id === bookId);
  if (!book) {
    return res.status(404).json({ message: 'Book not found' });
  }
  book.title = title;
  book.author = author;
  res.json(book);
});

// Delete a book by ID
app.delete('/books/:id', (req, res) => {
  const bookId = parseInt(req.params.id);
  const bookIndex = books.findIndex(book => book.id === bookId);
  if (bookIndex === -1) {
    return res.status(404).json({ message: 'Book not found' });
  }
  const deletedBook = books.splice(bookIndex, 1);
  res.json(deletedBook[0]);
});

app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});
