import axios from 'axios'

// 인증 요구X api 요청 시
const instance = axios.create({
  baseURL: process.env.VUE_APP_API,
  timeout: 180000,
  withCredentials: false,
  headers: {
    'Content-Type': 'application/json'
  }
})
export default instance
