import authHttp from './auth-http'

export function getPost(postId) {
  return authHttp.get(`/api/posts/${postId}`)
}

export function getPostList() {
  return authHttp.get('/api/posts', { params: { order: 'new' } })
}

export function likes(postId) {
  return authHttp.post(`/api/posts/${postId}/likes`)
}
export function scrap(postId) {
  return authHttp.post(`/api/posts/${postId}/scrap`)
}

export function unlikes(postId) {
  return authHttp.delete(`/api/posts/${postId}/likes`)
}
export function unscrap(postId) {
  return authHttp.delete(`/api/posts/${postId}/scrap`)
}
