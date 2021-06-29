import { createStore } from 'vuex'

export default createStore({
  state: {
    tasks: [],
    firstName: null,
    lastName: null,
    token: null,
  },
  getters: {
    fullName(state) {
      return state.firstName + ' ' + state.lastName;
    },
    token(state) {
      return state.token;
    },
    tasks(state) {
      return state.tasks;
    }
  },
  mutations: {
    login(state, user) {
      state.firstName = user.firstName;
      state.lastName = user.lastName;
      state.token = user.token;
      window.localStorage.setItem('user', JSON.stringify(user));
    },
    logout(state) {
      state.firstName = null;
      state.lastName = null;
      state.token = null;
      window.localStorage.removeItem('user');
    },
    addTask(state, newTask) {
      state.tasks.push(newTask);
    },
    setTasks(state, tasks) {
      state.tasks = tasks;
    },
    updateTask(state, taskToUpdate) {
      state.tasks = state.tasks.map(task => {
        if (task.id === taskToUpdate.id) {
          return taskToUpdate;
        } else {
          return task;
        }
      })
    },
    deleteTask(state, id) {
      state.tasks = state.tasks.filter(task => task.id !== id);
    }
  }
})
