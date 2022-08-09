import constant from '@/constant'
export default function authHeader() {
  let header = {}
  const accessToken = localStorage.getItem(constant.ACCESS_TOKEN)
  if (accessToken) {
    header = { Authorization: `Bearer ${accessToken}` }
  }
  return header
}
