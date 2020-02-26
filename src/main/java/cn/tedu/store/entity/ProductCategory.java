package cn.tedu.store.entity;

/**
 * 商品分类的类型
 * @author Administrator
 *
 */
public class ProductCategory extends BaseEntity {
  /**
	 * 
	 */
	private static final long serialVersionUID = -7964685236972107921L;
private String  id;
  private String  parentId;
  private String name;
  private Integer status;
  private Integer sortOrder;
  private Integer isParent;
@Override
public String toString() {
	return "ProductCategory [id=" + id + ", parentId=" + parentId + ", name=" + name + ", status=" + status
			+ ", sortOrder=" + sortOrder + ", isParent=" + isParent + "]";
}
/**
 * @return the id
 */
public String getId() {
	return id;
}
/**
 * @param id the id to set
 */
public void setId(String id) {
	this.id = id;
}
/**
 * @return the parentId
 */
public String getParentId() {
	return parentId;
}
/**
 * @param parentId the parentId to set
 */
public void setParentId(String parentId) {
	this.parentId = parentId;
}
/**
 * @return the name
 */
public String getName() {
	return name;
}
/**
 * @param name the name to set
 */
public void setName(String name) {
	this.name = name;
}
/**
 * @return the status
 */
public Integer getStatus() {
	return status;
}
/**
 * @param status the status to set
 */
public void setStatus(Integer status) {
	this.status = status;
}
/**
 * @return the sortOrder
 */
public Integer getSortOrder() {
	return sortOrder;
}
/**
 * @param sortOrder the sortOrder to set
 */
public void setSortOrder(Integer sortOrder) {
	this.sortOrder = sortOrder;
}
/**
 * @return the isParent
 */
public Integer getIsParent() {
	return isParent;
}
/**
 * @param isParent the isParent to set
 */
public void setIsParent(Integer isParent) {
	this.isParent = isParent;
}
}
