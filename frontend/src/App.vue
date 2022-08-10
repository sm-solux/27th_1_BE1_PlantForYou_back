<template>
  <nav>
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link> |
    <br />

    <b-navbar-nav v-if="!loggedIn">
      <a :href="GOOGLE_AUTH_URL">구글 로그인</a>
    </b-navbar-nav>
    <br />

    <b-navbar-nav v-if="loggedIn">
      <router-link to="/test">Test</router-link> |
      <a href="href" @click.prevent="logout">로그아웃</a>
    </b-navbar-nav>
  </nav>
  <router-view />
</template>

<script>
import constant from '@/constant'
export default {
  data() {
    return {
      loggedIn: this.$store.state.auth.loggedIn,
      GOOGLE_AUTH_URL: constant.GOOGLE_AUTH_URL
    }
  },
  methods: {
    logout() {
      this.$store.dispatch('auth/logout')
      location.href = '/'
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 35px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
