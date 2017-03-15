package com.baomidou.test.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;


/**
 * <p>
 * 
 * </p>
 *
 * @author Yanghu
 * @since 2017-03-14
 */
public class Tb1 extends Model<Tb1> {

    private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private Integer status;
	private String description;
    /**
     * 名称2
     */
	@TableField("second_name")
	private String secondName;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
