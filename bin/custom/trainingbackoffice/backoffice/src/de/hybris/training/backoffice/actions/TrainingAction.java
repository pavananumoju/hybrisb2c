/**
 *
 */
package de.hybris.training.backoffice.actions;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.HashSet;

import javax.annotation.Resource;

import org.zkoss.zul.Messagebox;

import com.hybris.cockpitng.actions.ActionContext;
import com.hybris.cockpitng.actions.ActionResult;
import com.hybris.cockpitng.actions.CockpitAction;

/**
 * @author pavananumoju
 *
 */


public class TrainingAction implements CockpitAction<Object, Object>
{


	@Resource(name = "modelService")
	private ModelService modelService;

	@Resource
	private CommonI18NService commonI18NService;

	@Override
	public ActionResult<Object> perform(final ActionContext<Object> actionContext)
	{
		HashSet<Object> dataList = new HashSet<Object>();
		if (actionContext.getData() instanceof CustomerModel)
		{
			dataList.add(actionContext.getData());
		}
		else
		{

			dataList = (HashSet<Object>) actionContext.getData();
		}

		if (dataList.iterator().next() instanceof CustomerModel)
		{
			for (final Object o : dataList)
			{
				final CustomerModel b2BCustomerModel = (CustomerModel) o;

				if (b2BCustomerModel.isLoginDisabled())
				{

					b2BCustomerModel.setToken("SecureTOkenString_test");

					modelService.save(b2BCustomerModel);
					System.out.println("Action triggered");
				}
			}
			Messagebox.show(actionContext.getLabel("action.send.registration.invite.sent"),
					actionContext.getLabel("action.send.registration.invite.sent.title"), Messagebox.OK, Messagebox.INFORMATION);
			return new ActionResult<Object>(ActionResult.SUCCESS);
		}

		Messagebox.show(dataList + " (" + ActionResult.ERROR + ")",
				actionContext.getLabel("action.send.registration.invite.sent.title"), Messagebox.OK, Messagebox.ERROR);
		return new ActionResult<Object>(ActionResult.ERROR);
	}

	@Override
	public boolean canPerform(final ActionContext<Object> ctx)
	{
		HashSet<Object> dataList = new HashSet<Object>();
		if (ctx.getData() instanceof CustomerModel)
		{
			dataList.add(ctx.getData());
		}
		else
		{

			dataList = (HashSet<Object>) ctx.getData();
		}
		if (dataList != null)
		{
			boolean canPerform = false;
			for (final Object o : dataList)
			{
				final CustomerModel b2bCustomer = (CustomerModel) o;
				if (b2bCustomer.isLoginDisabled())
				{
					canPerform = true;

					break;
				}
			}
			return canPerform && dataList.iterator().next() instanceof CustomerModel;
		}

		return false;
	}

	@Override
	public boolean needsConfirmation(final ActionContext<Object> ctx)
	{
		return true;
	}

	@Override
	public String getConfirmationMessage(final ActionContext<Object> ctx)
	{
		return ctx.getLabel("action.send.registration.invite.confirm");
	}


}
