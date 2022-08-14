<!-- eslint-disable no-tabs -->
<template>
  <div></div>
</template>
<script>
import constant from '@/constant'
export default {
  created() {
    this.validateToken()
  },
  methods: {
    getUrlParameter(key) {
      key = key.replace(/[[]/, '\\[').replace(/[\]]/, '\\]')
      const regex = new RegExp('[\\?&]' + key + '=([^&#]*)')
      const results = regex.exec(location.search)
      return results === null ? '' : decodeURI(results[1].replace(/\+/g, ' '))
    },

    validateToken() {
      const accessToken = this.getUrlParameter('accessToken')
      const refreshToken = this.getUrlParameter('refreshToken')

      if (accessToken) {
        localStorage.setItem(constant.ACCESS_TOKEN, accessToken)
        localStorage.setItem(constant.REFRESH_TOKEN, refreshToken)
        location.href = '/'
      } else {
        const error = this.getUrlParameter('error')
        console.log(error)
        this.$router.push('/')
      }
    }
  }
}
</script>
