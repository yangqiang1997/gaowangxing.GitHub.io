import {server} from '@/tools/servers'

export class resourceApi{

  static getResourceInfo(params){
   return  server.connection('GET','/api/resource',params)
}

  static getTotalResource(param){
  return  server.connection('GET','/api/resources',param)
  }

  static addResourceInfo(params){
   return  server.connection('POST','/api/resource',params)
  }

  static updateResourceInfo(params){
    return server.connection('PUT','/api/resource',params)
  }

  static getSourceList(params){
  return server.connection('GET','/api/resources',params);
  }

  static delData(param){
    return server.connection('DELETE','/api/resource',param)
  }
}
