import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { removeTodo, Todo } from '../features/todos/todoSlice';


function Todos() {
  const todos = useSelector(state => state) as Todo[];
  const dispatch = useDispatch();

  return (
    <>
      {todos.map(todo => {
        return (
          <div className="todo" key={todo.id}>
            {todo.title}
            <button style={{padding:4, }} onClick={()=> dispatch(removeTodo(todo.id))}>X</button>
          </div>
        )
      })}
    </>
  )
}

export default Todos