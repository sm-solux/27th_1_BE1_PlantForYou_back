/* eslint-disable */
import axios from 'axios'
import authHeader from './auth-header'
import http from './http'
import store from '@/store'
import constant from '@/constant'

// 인증 요구O api 요청 시
const instance = axios.create({
  baseURL: process.env.VUE_APP_API,
  timeout: 180000,
  withCredentials: false,
  headers: {
    'Content-Type': 'application/json'
  }
})

// 여기서 accessToken을 헤더에 삽입
instance.interceptors.request.use((config) => {
  config['headers'] = authHeader()
  return config
})

// 여기서 accessToken 만료 시, refreshToken으로 토큰 재발급 후 재요청
instance.interceptors.response.use(
  (res) => {
    return res
  },
  async (err) => {
    const originalReq = err.config
    if (
      err.response.status === 401 &&
      err.config &&
      !err.config.__isRetryRequest
    ) {
      originalReq._retry = true
      const res = await http
        .post('/auth/refresh', {
          accessToken: localStorage.getItem(constant.ACCESS_TOKEN),
          refreshToken: localStorage.getItem(constant.REFRESH_TOKEN)
        })
        .catch(() => {
          // 재발급하는 과정에서 오류생기면 무조건 재로그인
          store.dispatch('auth/logout')
          location.href = '/'
          return
        })

      const data = res.data
      localStorage.setItem(constant.ACCESS_TOKEN, data.accessToken)
      localStorage.setItem(constant.REFRESH_TOKEN, data.refreshToken)
      originalReq.headers.Authorization = `Bearer ${data.accessToken}`
      return axios(originalReq)
    }
    return Promise.reject(err)
  }
)
export default instance
