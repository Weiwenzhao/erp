/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const tableRouter = {
  path: '/table',
  component: Layout,
  redirect: '/table/complex-table',
  name: 'Table',
  meta: {
    title: 'Table',
    icon: 'table'
  },
  children: [
    {
      path: 'dynamic-table',
      component: () => import('@/views/table/dynamic-table/index'),
      name: 'DynamicTable',
      meta: { title: 'Dynamic Table' }
    },
    {
      path: 'drag-table',
      component: () => import('@/views/table/drag-table'),
      name: 'DragTable',
      meta: { title: 'Drag Table' }
    },
    {
      path: 'inline-edit-table',
      component: () => import('@/views/table/inline-edit-table'),
      name: 'InlineEditTable',
      meta: { title: 'Inline Edit' }
    },
    {
      path: 'complex-table',
      component: () => import('@/views/table/complex-table'),
      name: 'ComplexTable',
      meta: { title: 'Complex Table' }
    },
    {
      path: 'pwd-encrypt',
      component: () => import('@/views/table/encrypt'),
      name: '密码本',
      meta: { title: '加密信息' }
    },
    {
      path: 'role',
      component: () => import('@/views/table/role'),
      name: '角色管理',
      meta: { title: '角色管理' }
    },
    {
      path: 'config',
      component: () => import('@/views/table/config'),
      name: '配置管理',
      meta: { title: '系统配置' }
    }
  ]
}
export default tableRouter
