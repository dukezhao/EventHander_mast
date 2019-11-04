package com.pitt.eventhander_mast;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

/**
 * Author: Z.King.James
 * Declarations:
 * Created on: 2019/11/3:17:16
 * Mail:mrzhaoxiaolin@163.com
 */
public class MyPwdView extends AppCompatEditText implements TextWatcher {
    public interface OnPwdInputComplete{
        void onPwdInputComplete(String s);
    }
    private OnPwdInputComplete mOnPwdInputComplete;

    private static final int DEFAULT_PWD_LENGTH = 6;
    private static final int DEFAULT_PWD_COLOR = 0xFF0B0B0B;
    private Paint paint;
    private int lineHeight;//pwd height
    private int contentWidth;//whole view width
    private int mPasswordLength;
    private int mPasswordColor;

    public MyPwdView(Context context) {
        this(context, null);
    }

    public MyPwdView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public MyPwdView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setCursorVisible(false);
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.PasswordView);
        mPasswordLength = t.getInt(R.styleable.PasswordView_pwd_length, DEFAULT_PWD_LENGTH);
        mPasswordColor = t.getColor(R.styleable.PasswordView_pwd_color, DEFAULT_PWD_COLOR);
        t.recycle();

        paint = new Paint();

        addTextChangedListener(this);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int width = widthSize;
        int height = (getPaddingTop() + (width - getPaddingLeft() - getPaddingRight()) / mPasswordLength + getPaddingBottom());
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        lineHeight = getHeight() - getPaddingBottom();
        contentWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        hideText();
        drawbackground(canvas);
        drawPwdGround(canvas);

        if(getText().length()==mPasswordLength&& mOnPwdInputComplete!=null)
        {
            mOnPwdInputComplete.onPwdInputComplete(getText().toString());
            mOnPwdInputComplete=null;
        }
    }

    public void hideText() {
        setTextSize(0);
    }

    public void drawbackground(Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE);

        paint.setColor(mPasswordColor);
        paint.setAntiAlias(true);
        canvas.drawRect(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(),
                getHeight() - getPaddingBottom(), paint);//最外部rect
        float[] positions = new float[4 * mPasswordLength];
        int index = 0;
        for (int i = 0; i < mPasswordLength; i++) {
            float[] line = {getPaddingLeft() + contentWidth * (i + 1) / mPasswordLength, getPaddingTop(),
                    getPaddingLeft() + contentWidth * (i + 1) / mPasswordLength, lineHeight};
            System.arraycopy(line, 0, positions, index, line.length);
            index = (i + 1) * line.length;
        }
        canvas.drawLines(positions, paint);
    }

    public void drawPwdGround(Canvas canvas) {
        float space = contentWidth / mPasswordLength;
        paint.setColor(mPasswordColor);
        paint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < getText().length(); i++) {
            canvas.drawCircle(getPaddingLeft() + space * (0.5f + i), getPaddingTop() + space / 2, space / 5, paint);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        hideText();
        int t=s.toString().length();
        if(t>mPasswordLength)
        {
            setText(s.toString().toCharArray(),0,mPasswordLength);
            setSelection(mPasswordLength);
        }
        invalidate();
    }

    public OnPwdInputComplete getOnPasswordInputComplete() {
        return mOnPwdInputComplete;
    }

    public void setOnPasswordInputComplete(OnPwdInputComplete onPasswordInputComplete) {
        this.mOnPwdInputComplete = onPasswordInputComplete;
    }
}
