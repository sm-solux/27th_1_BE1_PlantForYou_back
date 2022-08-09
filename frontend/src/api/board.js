import http from './http'

export function getPost(postId) {
  return http.get(`/api/posts/${postId}`)
}

export function likes(postId) {
  return http.post(`/api/posts/likes/${postId}`)
}
export function scrap(postId) {
  return http.post(`/api/posts/scrap/${postId}`)
}

export function unlikes(postId) {
  return http.delete(`/api/posts/likes/${postId}`)
}
export function unscrap(postId) {
  return http.delete(`/api/posts/scrap/${postId}`)
}
