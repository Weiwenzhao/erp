import request from '@/utils/request'

export function getRoutes() {
  return request({
    url: '/routes',
    method: 'get'
  })
}

export function getRoles(data) {
  return request({
    url: '/role/findAllRole',
    method: 'post',
    data
  })
}

export function addRole(data) {
  return request({
    url: '/role/add',
    method: 'post',
    data
  })
}

export function updateRole(id, data) {
  return request({
    url: `/role/${id}`,
    method: 'put',
    data
  })
}

export function deleteRole(id) {
  return request({
    url: `/role/${id}`,
    method: 'delete'
  })
}
