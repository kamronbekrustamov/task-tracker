<template>
  <div :key="task.id" v-for="task in tasks" @dblclick="updateTask(task)">
    <div :class="[task.important ? 'important' : '', 'task']">
      <h3>
        {{ task.title }}
        <i @click="deleteTask(task.id)" class="fas fa-times"></i>
      </h3>
      <p>{{ task.time }}</p>
    </div>
  </div>
  <h3 align="center" v-if="tasks.length == 0">No Tasks Found</h3>
</template>

<script>
export default {
  computed: {
    tasks() {
      return this.$store.getters.tasks;
    }
  },
  methods: {
    async fetchTasks() {
      const response = await fetch('/api/v1/tasks', {
        headers: {
          'Authorization': 'Bearer ' + this.$store.getters.token,
        }
      });
      if (response.ok) {
        const result = await response.json();
        this.$store.commit('setTasks', result);
      } else if (response.status === 401) {
        this.$store.commit('logout');
        this.$router.push('/login');
      } else {
        alert('Error occurred')
      }
    },
    async deleteTask(id) {
      const response = await fetch('/api/v1/tasks/' + id, {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.$store.getters.token,
        }
      });

      if (response.ok) {
        this.$store.commit('deleteTask', id);
      } else if (response.status === 401) {
        this.$store.commit('logout');
        this.$router.push('/login');
      } else {
        alert('Error occurred')
      }      
    },
    async updateTask(task) {
      task.important = !task.important;
      
      const response = await fetch('/api/v1/tasks/' + task.id, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.$store.getters.token,
        },
        body: JSON.stringify(task)
      });

      if (response.ok) {
        this.$store.commit('updateTask', task);
      } else if (response.status === 401) {
        this.$store.commit('logout');
        this.$router.push('/login');
      } else {
        alert('Error occurred')
      }      
    }
  },
  mounted() {
    this.fetchTasks();
  }
};
</script>

<style scoped>
.fas {
  color: red;
}

.task {
  background: #f4f4f4;
  margin: 5px;
  padding: 10px 20px;
  cursor: pointer;
}

.task.important {
  border-left: 5px solid green;
}

.task h3 {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
</style>