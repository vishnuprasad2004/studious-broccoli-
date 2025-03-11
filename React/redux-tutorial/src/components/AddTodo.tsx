import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { addTodo } from '../features/todos/todoSlice';

function AddTodo() {
  const [input, setInput] = useState('');
  // dispatch is a function that uses a reducer to change the value of the state
  const dispatch = useDispatch();

  function addTodoHandler(e: React.FormEvent) {
    e.preventDefault();
    console.log("Add Todo");
    dispatch(addTodo(input));
    setInput('');
  }

  return (
    <form onSubmit={addTodoHandler} style={{display: 'flex', gap:10}}>
      <input 
        type="text" 
        placeholder="Add Todo" 
        className='input-dark' 
        value={input}
        onChange={(e)=> setInput(e.target.value)}
        />
      <button type="submit">Add</button>
    </form>
  )
}

export default AddTodo