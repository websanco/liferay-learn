<a href="#" id="5"></a>

## Adding Style Books

<div class="ahead">

#### Exercise Goals

* Create a style book in the UI

</div>

<!--
#### Add Custom CSS Files
1. **Go to** the `css` folder from the `livingstone-fjord-theme/src` directory.
* **Add** a new folder under `/css` called `custom_properties`.
* **Create** two new files in the `custom_properties` folder:<br />
    `_custom_properties_set.scss`<br />
    `_custom_properties_variables.scss`

#### Add Custom Properties Variables to the Theme
1. **Open** the `_custom_properties_variables.scss` file.
* **Type** `lfr` to view the available code snippets.
* **Choose** the `11-custom-properties-variables` snippet.
* **Save** the file.
  * Alternatively, you can type and save the following:

```css
$custom-properties-spacers: ();
$custom-properties-spacers: map-merge(
                (
                        0: var(--spacer-0),
                        1: var(--spacer-1),
                        2: var(--spacer-2),
                        3: var(--spacer-3),
                        4: var(--spacer-4),
                        5: var(--spacer-5),
                        6: var(--spacer-6),
                        7: var(--spacer-7),
                        8: var(--spacer-8),
                        9: var(--spacer-9),
                        10: var(--spacer-10),
                ),
                $custom-properties-spacers
);

:root {
  // Colors
  --black: #{$black};
  --gray-100: #{$gray-100};
  --gray-200: #{$gray-200};
  --gray-300: #{$gray-300};
  --gray-400: #{$gray-400};
  --gray-500: #{$gray-500};
  --gray-600: #{$gray-600};
  --gray-700: #{$gray-700};
  --gray-800: #{$gray-800};
  --gray-900: #{$gray-900};
  --transparent: transparent;
  --white: #{$white};
  @each $key, $value in $theme-colors {
    --#{$key}: #{$value};
  }

  // Fonts
  --font-family-base: #{$font-family-base};
  --font-family-monospace: #{$font-family-monospace};
  --font-family-sans-serif: #{$font-family-sans-serif};
  --font-size-base: #{$font-size-base};
  --font-size-lg: #{$font-size-lg};
  --font-size-sm: #{$font-size-sm};
  --font-weight-bold: #{$font-weight-bold};
  --font-weight-bolder: #{$font-weight-bolder};
  --font-weight-light: #{$font-weight-light};
  --font-weight-lighter: #{$font-weight-lighter};
  --font-weight-normal: #{$font-weight-normal};
  --font-weight-semi-bold: #{$font-weight-semi-bold};

  // Headings
  --h1-font-size: #{$h1-font-size};
  --h2-font-size: #{$h2-font-size};
  --h3-font-size: #{$h3-font-size};
  --h4-font-size: #{$h4-font-size};
  --h5-font-size: #{$h5-font-size};
  --h6-font-size: #{$h6-font-size};

  // Spacers
  --spacer: #{$spacer};
  @each $key, $value in $spacers {
    $multiplier: nth($value, 1) / $spacer;
    @if ($value == 0) {
      $multiplier: 0;
    }
    --spacer-#{$key}: calc(var(--spacer) * #{$multiplier});
  }
}
```

<div class="page"></div>

#### Add Custom Properties Set to the Theme
1. Open the `_custom_properties_set.scss`.
* **Type** `lfr` to view the available code snippets.
* **Choose** the `12-custom-properties-set` snippet.
* **Save** the file.  
  * Alternatively, you can type and save the following:

```css
// Headers
h1,
.h1 {
  font-size: var(--h1-font-size);
}
h2,
.h2 {
  font-size: var(--h2-font-size);
}
h3,
.h3 {
  font-size: var(--h3-font-size);
}
h4,
.h4 {
  font-size: var(--h4-font-size);
}
h5,
.h5 {
  font-size: var(--h5-font-size);
}
h6,
.h6 {
  font-size: var(--h6-font-size);
}

// Spacers
@each $breakpoint in map-keys($grid-breakpoints) {
  $infix: breakpoint-infix($breakpoint, $grid-breakpoints);
  @include media-breakpoint-up($breakpoint) {
    @each $size, $length in $custom-properties-spacers {
      @each $library in ('bs', 'clay') {
        $important: '';
        $prefix: '';
        @if ($library == 'bs') {
          $important: '!important';
        } @else {
          $prefix: 'c-';
        }
        @each $prop, $abbrev in (margin: m, padding: p) {
          $variants: ('positive');
          @if ($prop == 'margin') {
            $variants: ('positive', 'negative');
          }
          @each $variant in $variants {
            $variation: '';
            $value: $length;
            @if ($variant != 'negative' or $size != 0) {
              @if ($variant == 'negative') {
                $value: calc(#{$length} * -1);
                $variation: 'n';
              }
              .#{$prefix}#{$abbrev}#{$infix}-#{$variation}#{$size} {
                #{$prop}: #{$value} #{$important};
              }
              .#{$prefix}#{$abbrev}t#{$infix}-#{$variation}#{$size},
              .#{$prefix}#{$abbrev}y#{$infix}-#{$variation}#{$size} {
                #{$prop}-top: #{$value} #{$important};
              }
              .#{$prefix}#{$abbrev}r#{$infix}-#{$variation}#{$size},
              .#{$prefix}#{$abbrev}x#{$infix}-#{$variation}#{$size} {
                #{$prop}-right: #{$value} #{$important};
              }
              .#{$prefix}#{$abbrev}b#{$infix}-#{$variation}#{$size},
              .#{$prefix}#{$abbrev}y#{$infix}-#{$variation}#{$size} {
                #{$prop}-bottom: #{$value} #{$important};
              }
              .#{$prefix}#{$abbrev}l#{$infix}-#{$variation}#{$size},
              .#{$prefix}#{$abbrev}x#{$infix}-#{$variation}#{$size} {
                #{$prop}-left: #{$value} #{$important};
              }
            }
          }
        }
      }
    }
  }
}

// Text format
.font-weight-bold {
  font-weight: var(--font-weight-bold) !important;
}
.font-weight-bolder {
  font-weight: var(--font-weight-bolder) !important;
}
.font-weight-light {
  font-weight: var(--font-weight-light) !important;
}
.font-weight-lighter {
  font-weight: var(--font-weight-lighter) !important;
}
.font-weight-normal {
  font-weight: var(--font-weight-normal) !important;
}
.font-weight-semi-bold {
  font-weight: var(--font-weight-semi-bold) !important;
}
```

#### Import Custom Properties Files
1. Go to the `/css` folder.
* **Add** a new file called `_custom_properties.scss`.
* **Import** the files from the `custom_properties` folder in this new file by typing the following:

```css
@import 'custom_properties/custom_properties_variables';
@import 'custom_properties/custom_properties_set';
```

#### Add Token Definitions
1. **Go to** the exercise-src folder in the `02-create-site-brand` directory.
* **Copy** the file called `frontend-token-definition.json` and paste it in the `WEB-INF` folder of your theme project.
  - Look through the file for three different categories: color-system, spacing, typography

#### Import Custom Properties to `_custom.scss`
1. **Go to** the `css` folder in the `livingstone-fjord-theme/src` directory.
* **Open** the file `/css/_custom.scss`.
* **Add** an import statement in the last position and save the file:

```css
@import "custom_properties";
```

<div class="page"></div>
-->

#### Change the Theme to Classic
1. **Go to** localhost:8080 in your browser.
	* Log in to the portal as an admin if you have been logged out.
* **Open** the `Site Administration` Menu.
* **Go to** `Site Builder > Pages`.
* **Click** the gear icon next to _Public Pages_.
* **Click** the _Change Current Theme_ button near the bottom of the page.
* **Choose** the _Classic_ theme.
* **Click** _Save_ at the bottom of the page.

#### Create a Style Book
1. **Go to** `Design > Style Books` in the `Site Administration` Menu.
* **Click** the _Add_ button to create a new Style Book.
* **Type** `Livingstone BIG` as the _Name_.
* **Click** the drop-down at the top where it says `Color System`.
* **Choose** the Typography category.
* **Find** the field _Heading 1 Font Size_.
* **Choose** the _5rem_ value.
* **Click** _Publish_ to complete the Style Book.
* **Click** _OK_ in the pop-up.

<div class="page"></div>

#### Add a Content Page and Use the Style Book
1. **Go to** `Site Builder > Pages` in the _Site Administration_ menu.
* **Click** the _Add_ button to create a new page.
* **Choose** _Public Page_ from the drop-down.
* **Choose** _Blank_ as the page type from the Basic Templates.
* **Type** `Style Book` as the _Name_.
* **Drag** and drop the _Heading_ fragment onto the canvas.
* **Click** the _Page Design Options_ (paint roller) icon in the content pages toolbar.
* **Choose** the _Style Book_ tab.
* **Select** the _Livingstone BIG_ Style Book.
  - The heading should change to a much larger font.

<br />

---

#### Bonus Exercises
1. Create a new style book that changes the color-system of the theme based on what you think looks best.
