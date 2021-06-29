<template>
  <nav class="nav">
    <h3>Kamronbek Rustamov</h3>
    <button @click="logout">Logout</button>
  </nav>
</template>

<script>
export default {
  methods: {
    logout() {
      this.$router.push('/login');
    }
  }
}
</script>

<style scoped>
.nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border: 1px solid teal;
  padding: 10px;
  max-width: 500px;
  margin: 10px auto;
  border-radius: 5px;
}

.nav button {
  display: inline-block;
  background: #000;
  color: #fff;
  border: none;
  padding: 10px 20px;
  margin: 5px;
  border-radius: 5px;
  cursor: pointer;
  text-decoration: none;
  font-size: 15px;
  font-family: inherit;
}

</style>