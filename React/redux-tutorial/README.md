# Redux Toolkit Tutorial with React

Redux is a State Management tool, first developed by people at FaceBook, the tool known as "Flux" later renamed to be "Redux". Its main purpose was to simulate a **global** db of sorts in the frontend part. It is framework independent, i.e. it can be used with Vue, Angular, React etc. 

Before learning Redux for React, we were using the useContext API hook to create a global Provider and context (some values to store through the website).  

We use ```react-redux``` to use the stores, slices and reducers in the react code.

### What is "Redux"?

Redux is really:

- A single store containing "global" state
- Dispatching plain object actions to the store when something happens in the app
- Pure reducer functions looking at those actions and returning immutably updated state


