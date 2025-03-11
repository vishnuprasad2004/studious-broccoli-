import { createSlice, nanoid } from "@reduxjs/toolkit";

export type Todo = {
  id: string;
  title: string;
}

const initialState:Todo[] = [
  { id: "1", title: "Learn React" }
];

/**
 * state: the current state of the slice
 * action: the action object that was dispatched
 */

export const todoSlice = createSlice({
  name: "todo",
  initialState,
  reducers: {
    addTodo: (state, action) => {
      const newTodo = {id:nanoid(), title: action.payload};
      state.push(newTodo);
    },
    removeTodo: (state, action) => {
      return state.filter(todo => todo.id !== action.payload);
    }
  }
});

export const { addTodo, removeTodo } = todoSlice.actions;

export default todoSlice.reducer;