/**
 * <p>Title: IDialogBuilder.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.maxqueen.com</p>
 * @author Sola
 * @date 2013-10-10
 * @version 1.0
 */
package com.maxqueen.sola.interfaces;

import android.view.View;

/**
 * <p>
 * Title: IDialogBuilder
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.maxqueen.com
 * </p>
 * 
 * @author Sola
 * @date 2013-10-10
 */
public interface IViewListener extends IListener {

	void buildView(Object param);

	View getView();

	void onViewDestory(int sequenceId);

}
