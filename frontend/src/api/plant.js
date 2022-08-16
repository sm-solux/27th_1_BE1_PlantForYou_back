import http from './http'

export function getPlant(plantId) {
  return http.get(`/api/plants/${plantId}`)
}
