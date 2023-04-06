# Android Root Detector Java

RootDetector is a Java class that provides a more robust root detection mechanism for Android devices. It checks for various indicators to determine if a device is rooted or not, making it more difficult for attackers to bypass.

Please note that while this root detection method is more robust than some simpler approaches, it can still be bypassed by a determined attacker. To increase security, consider implementing server-side checks or using obfuscation and anti-tampering techniques.

## Features

- Checks build tags for test-keys
- Checks common superuser file paths
- Checks for the presence of known root management apps
- Checks for the existence of the `su` binary

## Usage

To use the `RootDetector` class, simply call the `isDeviceRooted()` method. This method will return `true` if the device is rooted or `false` otherwise.

```java
boolean isRooted = RootDetector.isDeviceRooted();
```

## Contributing

If you find any bugs or have suggestions for improvements, please feel free to open an issue or submit a pull request.

## Licence

This project is licensed under the MIT License - see the LICENSE file for details.
