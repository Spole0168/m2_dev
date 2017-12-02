package com.shang.service.test;

import java.util.List;

public class Post1Res {
	private List<ErrorItem> reasons;
	private String approvalFlag;
	private String errorDesc;
	private String policyRiskLevel;
	private String chargeFlag;
	public List<ErrorItem> getReasons() {
		return reasons;
	}
	public void setReasons(List<ErrorItem> reasons) {
		this.reasons = reasons;
	}
	public String getApprovalFlag() {
		return approvalFlag;
	}
	public void setApprovalFlag(String approvalFlag) {
		this.approvalFlag = approvalFlag;
	}
	public String getErrorDesc() {
		return errorDesc;
	}
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	public String getPolicyRiskLevel() {
		return policyRiskLevel;
	}
	public void setPolicyRiskLevel(String policyRiskLevel) {
		this.policyRiskLevel = policyRiskLevel;
	}
	public String getChargeFlag() {
		return chargeFlag;
	}
	public void setChargeFlag(String chargeFlag) {
		this.chargeFlag = chargeFlag;
	}
}
