// https://github.com/TeamAmaze/AmazeFileManager/tree/622d0f8b5fc75acb828e68dedb98c776ac9ca53c/app/src/main/java/com/amaze/filemanager/ui/views/Indicator.java#L126-L167
public class TempClass {
  public Indicator(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);

    final int density = (int) context.getResources().getDisplayMetrics().density;

    // Load attributes
    final TypedArray a =
        getContext().obtainStyledAttributes(attrs, R.styleable.Indicator, defStyle, 0);

    dotDiameter =
        a.getDimensionPixelSize(R.styleable.Indicator_dotDiameter, DEFAULT_DOT_SIZE * density);
    dotRadius = dotDiameter / 2;
    halfDotRadius = dotRadius / 2;
    gap = a.getDimensionPixelSize(R.styleable.Indicator_dotGap, DEFAULT_GAP * density);
    animDuration =
        (long) a.getInteger(R.styleable.Indicator_animationDuration, DEFAULT_ANIM_DURATION);
    animHalfDuration = animDuration / 2;
    selectedColour =
        a.getColor(R.styleable.Indicator_currentPageIndicatorColor, DEFAULT_SELECTED_COLOUR);
    // half transparent accent color
    unselectedColour =
        Color.argb(
            80, Color.red(selectedColour), Color.green(selectedColour), Color.blue(selectedColour));

    a.recycle();

    unselectedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    unselectedPaint.setColor(unselectedColour);
    selectedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    selectedPaint.setColor(selectedColour);
    interpolator = AnimUtils.getFastOutSlowInInterpolator(context);

    // create paths & rect now – reuse & rewind later
    combinedUnselectedPath = new Path();
    unselectedDotPath = new Path();
    unselectedDotLeftPath = new Path();
    unselectedDotRightPath = new Path();
    rectF = new RectF();

    addOnAttachStateChangeListener(this);
  }


}