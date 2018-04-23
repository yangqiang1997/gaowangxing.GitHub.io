import {server} from '@/tools/servers'

export class orgManagementApi {
  /**
  * 获取用户
  * 传入组织机构Id
  * */
  static GetUserListApi(organizationId) {
    return server.connection('GET', `/api/userOrg`, {"organizationId": organizationId})
  }
  /**获取树的数据*/
  static GetSourceListApi() {
    return server.connection('GET', '/api/orgs')
  }

  /**获取组织机构类型*/
  static GetOrgType() {
    return server.connection('GET', '/api/orgType')
  }

  /**
   * 新增组织机构
   * @param data
   * @returns {Promise.<any>}
   */
  static addOrgApi(data) {
    return server.connection('POST', `/api/org`, data)
  }

  /**
  * 删除组织机构
  * 传入组织机构的Id
  * */
  static DeleteAOrg(param) {
    return server.connection('DELETE', `/api/org`, param)
  }
  /**
  * 更改组织机构
  * 组织机构更新后的信息
  * */
  static updateOrgInfo(params) {
    return server.connection('PUT','/api/org',params)
  }
  /**
 * 获取单个组织机构
 * 传入orgId
 * */
  static getOrgInfo(params) {
    return  server.connection('GET','/api/org',params)
  }
}
