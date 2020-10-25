package com.adoujia.school.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

/**
 * @author fangcheng
 */

@Data
@NoArgsConstructor
@ApiModel(description = "返回的分页对象")
public class PageVO<T> {
    @ApiModelProperty(value = "内容")
    private List<T> content;
    @ApiModelProperty(value = "是否为第一页")
    private Boolean first;
    @ApiModelProperty(value = "最后一页")
    private Boolean last;
    @ApiModelProperty(value = "总页数")
    private Integer totalPages;
    @ApiModelProperty(value = "总数据条数")
    private Long totalElements;
    @ApiModelProperty(value = "每页元素数")
    private Integer size;
    @ApiModelProperty(value = "页码")
    private Integer number;
    @ApiModelProperty(value = "返回的元素数")
    private Integer numberOfElements;

    /**
     * Pageable转换成自定义的PageVO
     */
    public static <T> PageVO<T> of(Page<T> data) {
        return of(data, data.getContent());
    }

    /**
     * @param page     仅用来获取元信息
     * @param contents 使用这个而不是page里的内容
     * @param <T>      content type
     * @return page res
     */
    public static <T> PageVO<T> of(Page page, List<T> contents) {
        PageVO<T> res = new PageVO<>();
        res.setContent(contents);
        res.setFirst(page.isFirst());
        res.setLast(page.isLast());
        res.setSize(page.getSize());
        res.setNumber(page.getNumber());
        res.setNumberOfElements(page.getNumberOfElements());
        res.setTotalElements(page.getTotalElements());
        res.setTotalPages(page.getTotalPages());
        return res;
    }

    /**
     * return list as a page
     *
     * @param contents contents
     * @param <T>      content type
     * @return page
     */
    public static <T> PageVO<T> ofOnePage(List<T> contents) {
        PageVO<T> res = new PageVO<>();
        res.setContent(contents);
        res.setFirst(true);
        res.setLast(true);
        res.setSize(contents.size());
        res.setNumber(0);
        res.setNumberOfElements(contents.size());
        res.setTotalElements((long) contents.size());
        res.setTotalPages(1);
        return res;
    }

}
