import authHttp from './auth-http'

export function withdraw() {
  return authHttp.delete('/api/user')
}
export function getName() {
  return authHttp.get('/api/user/name')
}
export function setNickname(userNickname) {
  return authHttp.put('/api/user/name', { userNickname: userNickname })
}
