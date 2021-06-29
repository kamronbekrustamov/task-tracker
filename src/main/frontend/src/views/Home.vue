<template>
  <the-navbar></the-navbar>
  <div class="container">
    <add-task></add-task>
    <tasks></tasks>
    <the-footer></the-footer>
  </div>
</template>

<script>
import Tasks from '../components/Tasks'
import AddTask from '../components/AddTask'
import TheFooter from '../components/TheFooter.vue'
import TheNavbar from '../components/TheNavbar.vue'

export default {
  components: {
    'tasks': Tasks,
    'add-task': AddTask,
    'the-footer': TheFooter,
    'the-navbar': TheNavbar
  }
}
</script>
