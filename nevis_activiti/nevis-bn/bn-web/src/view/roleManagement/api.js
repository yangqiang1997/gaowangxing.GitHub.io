import {server} from '@/tools/servers'


export class RoleManagementApi {
    //得到所有角色的信息
    static GetListApi(data) {
        return server.connection('GET', '/api/roles',data)
    }

    //得到权限资源
    static GetSourceListApi(data) {
      return server.connection('GET', '/api/sourceList',data)
    }

    //得到一个角色的信息
    static GetARole(id) {
        return server.connection('GET', `/api/role`, {"id": id})
    }

    //获取一个角色的现有权限
    static GetARoleJurisdiction(id) {
      return server.connection('GET', `/api/roleSource`, {"id": id})
    }

    //更新角色
    static UpdateARole(data) {
      return server.connection('PUT', `/api/role`,data)
    }

    //新增角色
    static AddARole(data) {
      return server.connection('POST', `/api/role`, data)
    }

    //删除一个角色
    static DeleteARole(id) {
      return server.connection('DELETE', `/api/role/delete`, {"id": id})
    }

    //删除选定的角色
    static DeleteSelectRole(ids) {
      return server.connection('DELETE', `/api/roles/delete`, {"ids": ids})
    }

    //角色权限分配设置
    static RoleJurisdiction(data) {
      return server.connection('GET', `/api/roleSource/dstribution`, data)
    }

    //所有组织结构
    static GetAllOrgs() {
      return server.connection('GET', `/api/userOrgs`)
    }

    //分配数据范围
    static SetDataScope(data){
      return server.connection('GET', `/api/roleDataScope`, data)
    }

    //分配数据范围
    static GetDataScope(id){
      return server.connection('GET', `/api/roleDataScopeList`, {"id": id})
    }
}
