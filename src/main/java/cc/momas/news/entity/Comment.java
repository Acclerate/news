package cc.momas.news.entity;

import java.util.Date;

public class Comment {

	private Integer id;
	private String content;
	private String ip;
	private Integer newsId;
	private Integer userId;
	private Date createtime;
	private Date updatetime;
	private Byte status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", ip=" + ip + ", newsId=" + newsId + ", userId=" + userId
				+ ", createtime=" + createtime + ", updatetime=" + updatetime + ", status=" + status + "]";
	}

}
