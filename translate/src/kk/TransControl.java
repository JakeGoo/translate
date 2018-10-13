package kk;

import java.io.UnsupportedEncodingException;
import javax.swing.JTextArea;

public class TransControl {

	public TransControl(String toLanguage, String sourceText, JTextArea output) throws UnsupportedEncodingException{
		if(toLanguage == null) {
			// 语言没有被选择, 显示错误信息
			new ErrorWin("还没有选择目标语言啦!");
			return;
		}
		if(output == null) {
			// 没有输入文字, 显示错误信息
			new ErrorWin("还没有输入要翻译的文字啦！！");
			return;
		}
		// 将目标语言代号和文字传递
		String getResult = new CallApi().call(toLanguage, sourceText);
		if(getResult == null) {
			// 显示错误信息
			new ErrorWin("网络可能有问题哦!");
		}
		// 显示翻译结果
		output.setText("\n   " + getResult);
	}
}
