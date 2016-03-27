package com.zhonghong.doctor.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.zhonghong.doctor.R;


public class FlowIndicator extends View {
	private int count;
	private float space, radius;
	private int point_normal_color, point_selected_color;

	// 选中
	private int selected = 0;

	// background selected normal

	public FlowIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FlowIndicator);

		count = a.getInteger(R.styleable.FlowIndicator_count, 4);
		space = a.getDimension(R.styleable.FlowIndicator_space, 4);
		radius = a.getDimension(R.styleable.FlowIndicator_point_radius, 9);

		point_normal_color = a.getColor(R.styleable.FlowIndicator_point_normal_color, 0xff9600);
		point_selected_color = a.getColor(R.styleable.FlowIndicator_point_selected_color, 0xffffff);

	//	int sum = attrs.getAttributeCount();

		a.recycle();
	}

	public void setSelection(int index) {
		this.selected = index;
		invalidate();
	}

	public void setCount(int count) {
		if(count==1)
			count=0;
		this.count = count;
		invalidate();
	}

//	public void next() {
//		if (selected < count - 1)
//			selected++;
//		else
//			selected = 0;
//		invalidate();
//	}
//
//	public void previous() {
//		if (selected > 0)
//			selected--;
//		else
//			selected = count - 1;
//		invalidate();
//	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		@SuppressLint("DrawAllocation")
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		float width = (getWidth() - ((radius * 2 * count) + (space * (count - 1)))) / 2.f;
		for (int i = 0; i < count; i++) {
			if (i == selected)
				paint.setColor(point_selected_color);
			else
				paint.setColor(point_normal_color);
			canvas.drawCircle(width + getPaddingLeft() + radius + i * (space + radius + radius), getHeight() / 2, radius, paint);
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
	}

	private int measureWidth(int measureSpec) {
		int result;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);
		int Count=count+2;
		if (specMode == MeasureSpec.EXACTLY) {
			result = specSize;
		} else {
			result = (int) (getPaddingLeft() + getPaddingRight() + (Count * 2 * radius) + (Count - 1) * radius + 1);
			if (specMode == MeasureSpec.AT_MOST) {
				result = Math.min(result, specSize);
			}
		}
		return result;
	}

	private int measureHeight(int measureSpec) {
		int result;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		if (specMode == MeasureSpec.EXACTLY) {
			result = specSize;
		} else {
			result = (int) (2 * radius + getPaddingTop() + getPaddingBottom() + 1);
			if (specMode == MeasureSpec.AT_MOST) {
				result = Math.min(result, specSize);
			}
		}
		return result;
	}

}
