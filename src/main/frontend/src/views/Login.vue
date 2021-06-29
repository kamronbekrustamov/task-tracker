<template>
  <div class="main">
    <p class="sign" align="center">Sign in</p>
    <form class="form1" @submit.prevent="submit">
      <input class="username" type="email" align="center" placeholder="Username" v-model="email" required/>
      <input class="password" type="password" align="center" placeholder="Password" v-model="password" required/>
      <button class="submit" align="center">Sign in</button>
    </form>
    <div class="register">
      <h4>Not a User? <router-link to="/register">Sign Up!</router-link></h4>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      email: '',
      password: '',
    }
  },
  methods: {
    submit() {
      if (this.password < 8) {
        alert('Password must be at least 8 characters');
      } else {
        this.authenticate();
      }
    },
    async authenticate() {
      const response = await fetch('/api/v1/users/authenticate', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Basic ' + btoa(this.email + ':' + this.password)
        }
      })

      if (response.ok) {
        const result = await response.json();
        this.$store.commit('login', result);
        this.$router.push('/');
      } else if (response.status === 401) {
        alert('Email or password is incorrect. Try again');
      } else {
        alert('Error occurred');
      }
    }
  }
}
</script>

<style scoped>
.main {
  background-color: #ffffff;
  width: 400px;
  height: 360px;
  margin: 7em auto;
  border-radius: 1.5em;
  box-shadow: 0px 11px 35px 2px rgba(0, 0, 0, 0.14);
}

.sign {
  padding-top: 40px;
  color: #8c55aa;
  font-family: "Ubuntu", sans-serif;
  font-weight: bold;
  font-size: 23px;
}

.username {
  width: 76%;
  color: rgb(38, 50, 56);
  font-weight: 700;
  font-size: 14px;
  letter-spacing: 1px;
  background: rgba(136, 126, 126, 0.04);
  padding: 10px 20px;
  border: none;
  border-radius: 20px;
  outline: none;
  box-sizing: border-box;
  border: 2px solid rgba(0, 0, 0, 0.02);
  margin-bottom: 50px;
  margin-left: 46px;
  text-align: center;
  margin-bottom: 27px;
  font-family: "Ubuntu", sans-serif;
}

form.form1 {
  padding-top: 40px;
}

.password {
  width: 76%;
  color: rgb(38, 50, 56);
  font-weight: 700;
  font-size: 14px;
  letter-spacing: 1px;
  background: rgba(136, 126, 126, 0.04);
  padding: 10px 20px;
  border: none;
  border-radius: 20px;
  outline: none;
  box-sizing: border-box;
  border: 2px solid rgba(0, 0, 0, 0.02);
  margin-bottom: 50px;
  margin-left: 46px;
  text-align: center;
  margin-bottom: 27px;
  font-family: "Ubuntu", sans-serif;
}

.username:focus,
.password:focus {
  border: 2px solid rgba(0, 0, 0, 0.18) !important;
}

.submit {
  cursor: pointer;
  border-radius: 5em;
  color: #fff;
  background: linear-gradient(to right, #9c27b0, #e040fb);
  border: 0;
  padding-left: 40px;
  padding-right: 40px;
  padding-bottom: 10px;
  padding-top: 10px;
  font-family: "Ubuntu", sans-serif;
  margin-left: 35%;
  font-size: 13px;
  box-shadow: 0 0 20px 1px rgba(0, 0, 0, 0.04);
}
.register {
  display: flex;
  justify-content: center;
  padding-top: 10px;
}

.register h4 {
  color: #8c55aa;
}
.register h4 a {
  color: teal;
  border: none;
  background-color: white;
  font-size: 1.0rem;
}

.register h4 a:hover {
  cursor: pointer;
}
</style>