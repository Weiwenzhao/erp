import request from '@/utils/request'

export function find(data) {
  return request({
    url: '/config/find',
    method: 'post',
    data
  })
}

export function add(data) {
  return request({
    url: '/config/add',
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
