<template>
  <header>
    <h1>Task Tracker</h1>
    <button
      :style="{ background: isVisible ? 'red' : 'green' }"
      class="btn"
      @click="toggleVisibility">
      {{ isVisible ? 'Close' : 'Add Task' }}
    </button>
  </header>
  <form @submit.prevent="submit" class="add-form" v-if="isVisible">
    <div class="form-control">
      <label>Task</label>
      <input type="text" v-model="title" name="text" placeholder="Add Task" />
    </div>
    <div class="form-control">
      <label>Day & Time</label>
      <input
        type="text"
        v-model="time"
        name="day"
        placeholder="Add Day & Time"
      />
    </div>
    <div class="form-control form-control-check">
      <label>Is It Important?</label>
      <input type="checkbox" v-model="important" name="important" />
    </div>

    <input type="submit" value="Save Task" class="btn btn-block" />
  </form>
</template>

<script>
export default {
  data() {
    return {
      title: '',
      time: '',
      important: false,
      isVisible: false,
    }
  },
  methods: {
    toggleVisibility() {
      this.title = '';
      this.time = '';
      this.important = false;
      this.isVisible = !this.isVisible;
    },
    submit() {
      if (this.title === '' || this.time === '') {
        alert('Time and Task Title cannot be emty');
      } else {
        this.addNewTask();
      }
      this.isVisible = false;
    },
    async addNewTask() {
      const response = await fetch('/api/v1/tasks', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.$store.getters.token
        },
        body: JSON.stringify({
          title: this.title,
          time: this.time,
          important: this.important
        })
      })

      if (response.ok) {
        const result = await response.json();
        this.$store.commit('addTask', result);
      } else if (response.status === 401) {
        this.$router.push('/login');
      } else {
        alert('Error occurred');
      }
    }
  },
}
</script>

<style scoped>
header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.add-form {
  margin-bottom: 40px;
}

.form-control {
  margin: 20px 0;
}

.form-control label {
  display: block;
}

.form-control input {
  width: 100%;
  height: 40px;
  margin: 5px;
  padding: 3px 7px;
  font-size: 17px;
}

.form-control-check {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.form-control-check label {
  flex: 1;
}

.form-control-check input {
  flex: 2;
  height: 20px;
}
</style>
