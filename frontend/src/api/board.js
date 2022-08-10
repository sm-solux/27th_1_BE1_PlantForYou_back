import authHttp from './auth-http'

export function getPost(postId) {
  return authHttp.get(`/api/posts/${postId}`)
}

export function likes(postId) {
  return authHttp.post(`/api/posts/likes/${postId}`)
}
export function scrap(postId) {
  return authHttp.post(`/api/posts/scrap/${postId}`)
}

export function unlikes(postId) {
  return authHttp.delete(`/api/posts/likes/${postId}`)
}
export function unscrap(postId) {
  return authHttp.delete(`/api/posts/scrap/${postId}`)
}
