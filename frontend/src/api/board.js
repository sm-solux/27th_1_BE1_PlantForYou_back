import authHttp from './auth-http'

export function getPost(postId) {
  return authHttp.get(`/api/posts/${postId}`)
}
export function getPostOnly(postId) {
  return authHttp.get(`/api/posts/${postId}/only`)
}

export function getPostList(pageNumber) {
  return authHttp.get('/api/posts', {
    params: { order: 'new', page: pageNumber, size: 15 }
  })
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

export function createPost(body) {
  return authHttp.post('/api/posts', body)
}
export function editPost(postId, body) {
  return authHttp.put(`/api/posts/${postId}`, body)
}
export function deletePost(postId) {
  return authHttp.delete(`/api/posts/${postId}`)
}

export function saveComment(body) {
  return authHttp.post('/api/comments', body)
}
export function deleteComment(commentId) {
  return authHttp.delete(`/api/comments/${commentId}`)
}
