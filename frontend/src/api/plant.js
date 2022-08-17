import http from './http'

export function getPlant(plantId) {
  return http.get(`/api/plants/${plantId}`)
}
export function getPlantList() {
  return http.get('/api/plants')
}
