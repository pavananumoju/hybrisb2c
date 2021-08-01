/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */

package de.hybris.platform.configurablebundleaddon.forms;


import de.hybris.platform.validation.annotations.NotEmpty;

import javax.validation.constraints.Min;

/**
 * Bundle form for {@link de.hybris.platform.configurablebundleaddon.controllers.pages.BundleCartPageController}.
 */
public class AddBundleProductForm
{
    @NotEmpty(message = "bundle.error.empty.product")
    private String productId;
    @Min(-1)
    private int bundleNo;
    @Min(0)
    private int quantity;
    @NotEmpty(message = "bundle.error.empty.template")
    private String bundleId;

    public AddBundleProductForm()
    {
        bundleNo = -1;
        quantity = 1;
    }

    public String getProductId()
    {
        return productId;
    }

    public void setProductId(final String productId)
    {
        this.productId = productId;
    }

    public int getBundleNo()
    {
        return bundleNo;
    }

    public void setBundleNo(final int bundleNo)
    {
        this.bundleNo = bundleNo;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(final int quantity)
    {
        this.quantity = quantity;
    }

    public String getBundleId()
    {
        return bundleId;
    }

    public void setBundleId(final String bundleId)
    {
        this.bundleId = bundleId;
    }
}
