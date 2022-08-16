import authHttp from './auth-http'

export function getPost(postId) {
  return authHttp.get(`/api/posts/${postId}`)
}
export function getPostOnly(postId) {
  return authHttp.get(`/api/posts/${postId}/only`)
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

export function createPost(form) {
  return authHttp.post('/api/posts', {
    cat: form.cat,
    title: form.title,
    content: form.content
  })
}
export function editPost(postId, form) {
  return authHttp.put(`/api/posts/${postId}`, {
    cat: form.cat,
    title: form.title,
    content: form.content
  })
}
export function deletePost(postId) {
  return authHttp.delete(`/api/posts/${postId}`)
}
